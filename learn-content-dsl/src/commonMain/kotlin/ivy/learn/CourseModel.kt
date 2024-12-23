package ivy.learn

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
data class Course(
    override val id: CourseId,
    val name: String,
    val tagline: String,
    val image: ImageUrl,
    val lessons: List<LessonId>,
) : Identifiable<CourseId>

@Serializable
@JvmInline
value class CourseId(override val value: String) : Id