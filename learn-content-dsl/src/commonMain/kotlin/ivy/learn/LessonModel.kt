package ivy.learn

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
data class Lesson(
  override val id: LessonId,
  val name: String,
  val tagline: String,
  val image: ImageUrl,
  val content: LessonContent,
  val completed: Boolean,
  val premium: Boolean = false,
  val version: String? = null,
) : Identifiable<LessonId>

@Serializable
data class LessonContent(
  val rootItem: LessonItemId,
  val items: Map<LessonItemId, LessonItem>,
)

@Serializable
@JvmInline
value class LessonId(override val value: String) : Id

@Serializable
sealed interface LessonItem {
  val id: LessonItemId
}

@Serializable
@JvmInline
value class LessonItemId(val value: String)

/**
 * Item that doesn't branch the tree.
 */
interface LinearItem {
  val next: LessonItemId?
}

@Serializable
@SerialName("TextItem")
data class TextItem(
  override val id: LessonItemId,
  val text: String,
  val style: TextStyle,
  override val next: LessonItemId?,
) : LessonItem, LinearItem

@Serializable
@SerialName("CodeItem")
data class CodeItem(
  override val id: LessonItemId,
  val code: String,
  override val next: LessonItemId?,
) : LessonItem, LinearItem

@Serializable
enum class TextStyle {
  Heading,
  Body,
  BodySpacingMedium,
  BodySpacingLarge
}

@Serializable
@SerialName("QuestionItem")
data class QuestionItem(
  override val id: LessonItemId,
  val question: String,
  val clarification: String?,
  val answers: List<Answer>,
  val correct: Set<AnswerId>,
  override val next: LessonItemId?,
) : LessonItem, LinearItem

@Serializable
data class Answer(
  val id: AnswerId,
  val text: String,
  val explanation: String?,
)

@Serializable
@JvmInline
value class AnswerId(val value: String)

@Serializable
@SerialName("OpenQuestionItem")
data class OpenQuestionItem(
  override val id: LessonItemId,
  val question: String,
  val correctAnswer: String,
  override val next: LessonItemId?,
) : LessonItem, LinearItem

@Serializable
@SerialName("LinkItem")
data class LinkItem(
  override val id: LessonItemId,
  val text: String,
  val url: String,
  override val next: LessonItemId?,
) : LessonItem, LinearItem

@Serializable
@SerialName("LessonNavigationItem")
data class LessonNavigationItem(
  override val id: LessonItemId,
  val text: String,
  val onClick: LessonItemId,
  override val next: LessonItemId?,
) : LessonItem, LinearItem

@Serializable
@SerialName("LottieAnimationItem")
data class LottieAnimationItem(
  override val id: LessonItemId,
  val lottie: LottieAnimation,
  override val next: LessonItemId?,
) : LessonItem, LinearItem

@Serializable
@JvmInline
value class LottieAnimation(val url: String)

@Serializable
@SerialName("ImageItem")
data class ImageItem(
  override val id: LessonItemId,
  val image: ImageUrl,
  override val next: LessonItemId?,
) : LessonItem, LinearItem

@Serializable
@JvmInline
value class ImageUrl(val url: String)

@Serializable
@SerialName("ChoiceItem")
data class ChoiceItem(
  override val id: LessonItemId,
  val question: String,
  val options: List<ChoiceOption>,
) : LessonItem

@Serializable
data class ChoiceOption(
  val id: ChoiceOptionId,
  val text: String,
  val next: LessonItemId,
)

@Serializable
@JvmInline
value class ChoiceOptionId(val value: String)

@Serializable
@SerialName("MysteryItem")
data class MysteryItem(
  override val id: LessonItemId,
  val text: String,
  val hidden: LessonItemId,
  override val next: LessonItemId?
) : LessonItem, LinearItem

@Serializable
@SerialName("SoundItem")
data class SoundItem(
  override val id: LessonItemId,
  val text: String,
  val sound: SoundUrl,
  override val next: LessonItemId?
) : LessonItem, LinearItem

@Serializable
@JvmInline
value class SoundUrl(val url: String)