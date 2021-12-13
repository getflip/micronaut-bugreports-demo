package com.example.db

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import io.micronaut.data.annotation.Where
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.io.Serializable
import java.time.Instant
import java.util.UUID
import javax.persistence.*
import com.vladmihalcea.hibernate.type.json.JsonBinaryType

@Entity
@Table(name = "users")
data class UserWithWhereOnEntity(
    @Id
    val id: UUID,
    val email: String?,
    val deleted: Boolean
)


@Entity
@Table(name = "users")
@Where("deleted = false")
data class UserWithWhereOnRepository(
    @Id
    val id: UUID,
    val email: String?,
    val deleted: Boolean
)

@Entity
@Table(name = "users")
@Where("deleted = false")
data class UserWithoutWhere(
    @Id
    val id: UUID,
    val email: String?,
    val deleted: Boolean
)

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Entity
@Table(name = "posts")
@TypeDef(
    name = "jsonb",
    typeClass = JsonBinaryType::class
)
data class PostDTO(
    @Id
    val id: UUID,
    val groupId: UUID,
    val title: String,
    @Column(columnDefinition = "text")
    val body: String,
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    val formattedBody: JsonNode?,
    val authorId: UUID,
    val sequenceNumber: Long?,
    val postDeleted: Boolean,
    @OneToMany(cascade = [CascadeType.PERSIST], mappedBy = "postId", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    val attachments: Set<AttachmentDTO>,
    val createdAt: Instant,
    val updatedAt: Instant?,
    @OneToMany(mappedBy = "postId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    val comments: Set<MessageDTO>,
    @OneToMany(mappedBy = "postId", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    val reactions: Set<ReactionDTO>,
    val clientContentId: UUID?,
    val postInteractionFeaturesEnabled: Boolean,
    val scheduledAt: Instant?,
    val publishedAt: Instant?,
    @Enumerated(EnumType.STRING)
    val language: SourceLanguageDTO?,
    /*
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "posts_survey_choice_sets",
        joinColumns = [JoinColumn(name = "post_id")],
        inverseJoinColumns = [
            JoinColumn(name = "survey_choice_set_id", referencedColumnName = "id"),
            JoinColumn(name = "tenant", referencedColumnName = "tenant"),
        ],
    )
    val surveyChoiceSet: SurveyChoiceSetDTO?,
     */
)


@Entity
@Table(name = "attachments")
@Where("attachment_deleted = false")
data class AttachmentDTO(
    @Id
    val id: UUID,
    val messageId: UUID?,
    val postId: UUID?,
    val fileName: String,
    val mimeType: String,
    val attachmentDeleted: Boolean,
    val streamableVideo: Boolean,
    @Column(columnDefinition = "int2")
    val width: Short?,
    @Column(columnDefinition = "int2")
    val height: Short?,
    val size: Long?,
    val migrated: Boolean,
    @Column(columnDefinition = "int2")
    val orderNumber: Short?
)


@Entity
@Table(name = "messages")
data class MessageDTO(
    @Id
    val id: UUID,
    val sequenceNumber: Long,
    val chatId: UUID?,
    val postId: UUID?,
    @Column(columnDefinition = "text")
    val body: String,
    val authorId: UUID,
    val messageDeleted: Boolean,
    @OneToMany(cascade = [CascadeType.PERSIST], mappedBy = "messageId", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    val attachments: Set<AttachmentDTO>,
    val createdAt: Instant,
    @OneToMany(mappedBy = "messageId", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    val reactions: Set<ReactionDTO>,
    val clientContentId: UUID?
)

@Entity
@Table(name = "reactions")
data class ReactionDTO(
    @Id
    val id: UUID,
    val messageId: UUID?,
    val postId: UUID?,
    val userId: UUID,
    val createdAt: Instant,
    val symbol: String
)


enum class SourceLanguageDTO(@JsonValue val languageCode: LanguageIsoCode) {
    ARABIC(LanguageIsoCode.AR),
    UNKNOWN(LanguageIsoCode.AF),
}

enum class LanguageIsoCode {
    AF,
    AR,
}


@Entity
@Table(name = "survey_choice_sets")
data class SurveyChoiceSetDTO(
    @EmbeddedId
    val id: SurveyChoiceSetIdDTO,
    @Column(name = "created_at")
    val createdAt: Instant,
    @Column(name = "updated_at")
    val updatedAt: Instant,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumns(
        JoinColumn(name = "survey_choice_set_id", referencedColumnName = "id", insertable = true, updatable = true),
        JoinColumn(name = "tenant", referencedColumnName = "tenant", insertable = true, updatable = true),
    )
    val choices: Set<SurveyChoiceDTO>,
)


@Entity
@Table(name = "survey_choices")
data class SurveyChoiceDTO(
    @EmbeddedId
    val id: SurveyChoiceIdDTO,
    @Embedded
    @AttributeOverrides(
        AttributeOverride(
            name = "uuid",
            column = Column(name = "survey_choice_set_id"),
        ),
        AttributeOverride(
            name = "tenant",
            column = Column(
                name = "tenant",
                insertable = false,
                updatable = false,
            ),
        ),
    )
    val choiceSetId: SurveyChoiceSetIdDTO,
    @Column(name = "created_at")
    val createdAt: Instant,
    @Column(name = "updated_at")
    val updatedAt: Instant,
    @Column(name = "title")
    val title: String,
    @Column(name = "order_number")
    val orderNumber: Short,
    @Column(name = "vote_count")
    val voteCount: Int,
)

@Embeddable
data class SurveyChoiceIdDTO(
    @Column(name = "id")
    val uuid: UUID,
    @Column(name = "tenant")
    val tenant: String,
) : Serializable


@Embeddable
data class SurveyChoiceSetIdDTO(
    @Column(name = "id")
    val uuid: UUID,
    @Column(name = "tenant")
    val tenant: String,
) : Serializable