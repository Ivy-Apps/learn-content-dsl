package ivy.learn.llm

fun main() {
  val prompt = lessonPrompt(
    topic = "Time Complexity (Big O notation)",
    learningGoal = """
Understand what is Big O and be able to differentiate between O(1), O(n), O(logn), O(n^2), and O(nlogn).
   """.trimIndent(),
    structure = listOf(
      "Introduction",
      "Why Time Complexity Matters",
      "Big O Basics",
      "Detailed Breakdown of Key Complexities",
      "Interactive Exercises for Differentiating Complexities",
      "Real-World Applications of Big O",
      "Reflection and Problem-Solving"
    )
  )
  println("===== PROMPT ====")
  println(prompt)
  println("=====")
}

fun lessonPrompt(
  topic: String,
  learningGoal: String,
  structure: List<String>,
): String {
  return """
Create an interactive, beginner-friendly lesson to help learners understand $topic. 
The lesson should be:
1. Easy to follow: Use simple, clear language with short sections.
2. Highly visual and example-driven: Include code snippets and real-world analogies to explain concepts.
3. Engaging: Design with questions and activities learners can interact with, emphasizing key concepts.

# Structure:
${structure.mapIndexed { index, bullet -> "${index + 1}. $bullet" }.joinToString(separator = "\n")}

**Tone**: Friendly, encouraging, and engaging, aimed at learners new to algorithms or computer science.

**Learning Goal**: Ensure learners can "$learningGoal" with confidence.
    
Create an interactive, beginner-friendly lesson to help learners understand: "$topic". The lesson should be:
1. Easy to follow: Use simple, clear language with short sections.
2. Highly visual and example-driven: Include code snippets and real-world analogies to explain concepts.
3. Engaging: Design with questions and activities learners can interact with, emphasizing key concepts.

Use only the Kotlin DSL defined below—no extra structures or deviations.

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
