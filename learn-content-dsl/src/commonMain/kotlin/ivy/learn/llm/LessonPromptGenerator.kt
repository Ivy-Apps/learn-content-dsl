package ivy.learn.llm

fun main() {

}

fun lessonPrompt(topic: String): String {
  return """
Create an interactive, short, bite-sized lesson on the topic: "$topic".
Use only the Kotlin DSL defined below—no extra structures or deviations.

Requirements:
1. Present a brief introduction (`text`).
2. Ask a "Why" question (`question`), then follow up with an explanation (`text`).
3. Include a section detailing internal mechanics or key concepts (`text` and optionally `image`).
4. Three or four questions teaching the mechanics with text explanation after each question.
5. At least one open-ended question (`openQuestion`) encouraging reflection or problem-solving.
6. Each question must have 2–4 answers (`answer`) with clear explanations for correctness or incorrectness.
7. Optionally, showcase a relevant code snippet using `text` + `codeBuilder`.

### Example Usage of DSL
```kotlin
fun exampleLesson() = lessonContent {
  intro()
  whyQuestion()
  whyExplanation()
  internalsSection()
  conceptQuestion()
  addOpenQuestion()
  codeExample()
}

// Introduction
private fun LessonContentScope.intro() {
  text("intro") {
    text = "Topic Title"
    style = TextStyle.Heading
  }
  text("intro_text") {
    text = "A brief description introducing the topic to learners."
  }
}

// Why Question
private fun LessonContentScope.whyQuestion() {
  question("why_q") {
    question = "A question prompting learners to think about the topic's importance."
    clarification = "Provide hints or context here."
    answer(text = "Correct Answer", correct = true, explanation = "Why this answer is correct.")
    answer(text = "Incorrect Answer", explanation = "Why this answer is not correct.")
  }
}

// Why Explanation
private fun LessonContentScope.whyExplanation() {
  text("why_explain") {
    text = "An explanation detailing why the topic is important or relevant."
    style = TextStyle.BodySpacingLarge
  }
}

// Internal Mechanics Section
private fun LessonContentScope.internalsSection() {
  text("internals") {
    text = "An explanation of the internal workings or key details of the topic."
    style = TextStyle.BodySpacingMedium
  }
  image("topic_diagram") {
    imageUrl = "https://example.com/example_image.png"
  }
}

// Concept Question
private fun LessonContentScope.conceptQuestion() {
  question("concept_q") {
    question = "A question to test understanding of a specific concept."
    answer(text = "Correct Answer", correct = true, explanation = "Why this is correct.")
    answer(text = "Incorrect Answer", explanation = "Why this is incorrect.")
  }
}

// Open Question
private fun LessonContentScope.addOpenQuestion() {
  openQuestion("open_q") {
    question = "A reflective or problem-solving question where the learner inputs the answer."
    correctAnswer = "The correct or expected answer."
  }
}

// Code Example
private fun LessonContentScope.codeExample() {
  text("code_example") {
    text = codeBuilder {
      line("// Example of code relevant to the topic")
      line("val example = \"This is a code snippet\"")
      line("println(example)")
    }
  }
}

# DSL Reference
```kotlin
interface LessonContentScope {
    @LearnCmsDsl
    fun text(id: String, next: String? = null, builder: TextScope.() -> Unit)

    @LearnCmsDsl
    fun question(id: String, builder: QuestionScope.() -> Unit)

    @LearnCmsDsl
    fun openQuestion(id: String, builder: OpenQuestionScope.() -> Unit)

    @LearnCmsDsl
    fun image(id: String, builder: ImageScope.() -> Unit)
}

interface TextScope {
    var style: TextStyle
    var text: String
}

@Serializable
enum class TextStyle {
    Heading,
    Body,
    BodySpacingMedium,
    BodySpacingLarge
}

interface QuestionScope {
    var question: String
    var clarification: String?

    @LearnCmsDsl
    fun answer(
        text: String,
        explanation: String? = null,
        correct: Boolean = false
    )
}

interface OpenQuestionScope {
    var question: String
    var correctAnswer: String
}

@TextBuilderDsl
fun textBuilder(builder: TextBuilderScope.() -> Unit): String {
    val scope = TextBuilder().apply(builder)
    return scope.build()
}

interface TextBuilderScope {
    @TextBuilderDsl
    fun line(text: String)

    @TextBuilderDsl
    fun bullet(text: String)

    @TextBuilderDsl
    fun newLine()
}

fun codeBuilder(builder: CodeBuilderScope.() -> Unit): String {
    val scope = CodeBuilder().apply(builder)
    return scope.build()
}

interface CodeBuilderScope {
    @CodeBuilderDsl
    fun line(text: String)
}
```
""".trimIndent()
}
