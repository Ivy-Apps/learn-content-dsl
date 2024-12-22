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
    ),
    priorKnowledge = emptyList(),
  )
  println("===== PROMPT ====")
  println(prompt)
  println("=====")
}

fun lessonPrompt(
  topic: String,
  learningGoal: String,
  structure: List<String>,
  priorKnowledge: List<String>,
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

Use only the Kotlin DSL defined below—no extra structures or deviations.
Ensure that the ids in the lesson are unique and that there are NO duplicated ids.

The lesson should be feel good, logically connected and explain the concept 
assuming no prior knowledge beside: "${priorKnowledge.joinToString(separator = ",")}"

### Example Usage of DSL
```kotlin

// Text
private fun LessonContentScope.text() {
  text("id") {
    text = "Topic Title"
    style = TextStyle.Heading
  }
}

private fun LessonContentScope.image() {
  image("topic_diagram") {
    imageUrl = "https://example.com/example_image.png"
  }
}

// Single Correct Answer (1 of 4)
private fun LessonContentScope.singleCorrectQuestion() {
  question("single_correct_q") {
    question = "Which option best represents the core concept?"
    clarification = "Select the most appropriate answer from the options."
    answer(text = "Correct Answer", correct = true, explanation = "This option aligns with the main idea.")
    answer(text = "Option 2", explanation = "This is partially correct but doesn't capture the full idea.")
    answer(text = "Option 3", explanation = "This is incorrect as it misunderstands the concept.")
    answer(text = "Option 4", explanation = "This is unrelated to the topic.")
  }
}

// Two Options Question (1 of 2)
private fun LessonContentScope.twoOptionsQuestion() {
  question("two_options_q") {
    question = "True or False: This concept is applicable to real-world problems."
    clarification = "Consider the practicality of applying this concept."
    answer(text = "True", correct = true, explanation = "Yes, this concept can be applied in many real-world scenarios.")
    answer(text = "False", explanation = "This is incorrect as the concept has practical applications.")
  }
}

// Multiple Correct Answers (N of M)
private fun LessonContentScope.multipleCorrectQuestion() {
  question("multiple_correct_q") {
    question = "Which of the following statements are true about the topic?"
    clarification = "Select all correct answers."
    answer(text = "Statement A", correct = true, explanation = "This is a true statement about the topic.")
    answer(text = "Statement B", explanation = "This is incorrect as it contradicts the topic.")
    answer(text = "Statement C", correct = true, explanation = "This statement aligns with the principles of the topic.")
    answer(text = "Statement D", explanation = "This is not true as it misrepresents the core idea.")
  }
}

// Code Example
private fun LessonContentScope.codeExample() {
  codeExample("code_example") {
    // Use Python for code examples
    text = codeBuilder {
      line("# Example demonstrating the application of the topic")
      line("def exampleFunction():")
      line("  print(\"This demonstrates the concept effectively.\")")
      line("")
      line("exampleFunction()")
    }
  }
}
```

# DSL Reference
```kotlin
interface LessonContentScope {
    @LearnCmsDsl
    fun text(id: String, next: String? = null, builder: TextScope.() -> Unit)

    @LearnCmsDsl
    fun question(id: String, builder: QuestionScope.() -> Unit)

    @LearnCmsDsl
    fun image(id: String, builder: ImageScope.() -> Unit)
}

interface TextScope {
    var style: TextStyle
    var text: String
}

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
