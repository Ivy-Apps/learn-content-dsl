package ivy.learn.llm

import ivy.learn.TextStyle

fun main() {
  val prompt = lessonPrompt(
    topic = "Time complexity: What and why?",
    learningGoal = """
Why we need time complexity and what is Big O notation. Only the basics and 101.
   """.trimIndent(),
    priorKnowledge = listOf("no prior knowledge"),
  )
  println("===== PROMPT ====")
  println(prompt)
  println("=====")
}

fun lessonPrompt(
  topic: String,
  learningGoal: String,
  priorKnowledge: List<String>,
): String {
  return """
You're an expert computer scientist making the best educational content on the Internet. Your task is to create an interactive, beginner-friendly lesson to help CS students with no prior knowledge understand "$topic". 
Based on the best methods and analogies in universities and books, teach "$learningGoal".

The lesson should be:
1. Easy to follow: Use simple, clear language with short sections. No fancy English words as it's designed for non-native speakers.
2. Highly example-driven: Include code snippets, every-day life, and real-world analogies to explain concepts.
3. Engaging: students should re-discover the lesson themselves by answering a series of simple questions that are close to reality.
4. Adaptable: The structure and DSL elements should be purposefully selected to achieve the learning goal.
5. Be practical: Include practical examples and analogies. Focus on concise, easy to follow and famous examples for demonstrating '$topic'.
6. Question-driven: The reader should re-invent and re-discover by answering many questions. Focus on short questions. The explanation of each question answer should  be the main tool to learn. Make answer explanations straightforward and simple to understand by giving real-world reasoning.
7. Question structure: Each questions must have 4 (four) possible answers. Sometimes you can have more than one correct. It's important the question to short and easy to understand with as few words as possible.
8. Code examples: Include short and simple code snippets in Python. All code examples must be in Python.
9. The list of questions and examples should form a comprehensive story optimized to achieve the learning goal.
10. Prioritize everyday-life and physical analogies that are common to people. Tap your knowledge base for the best analogies on '$learningGoal'.

# Structure:
Pick an appropriate structure for a short, byte-sized lesson that achieves the learning goal.

**Tone**: Friendly, encouraging, and engaging, aimed at learners new to algorithms or computer science. Students are non-native English speakers so make short sentences with simple words.

**Learning Goal**: Ensure students can "$learningGoal" with confidence.
The lesson should be designed in a way where the reader learns the concepts by answering a series of well-crafted and guiding questions.

**Requirements:**
- Use only the Kotlin DSL defined below—no extra structures or deviations.
- Ensure that the ids in the lesson are unique and that there are NO duplicated ids.
- The question text must be very short and easy to understand.
- Make sure to double-check that you've picked the simplest and most appropriate examples and analogies in your questions.

The lesson should feel coherent, logically connected, and accessible to learners with no prior knowledge besides: "${
    priorKnowledge.joinToString(
      separator = ", "
    )
  }".

## DSL Reference
```kotlin
interface LessonContentScope {
    @LearnCmsDsl
    fun text(id: String, builder: TextScope.() -> Unit)

    @LearnCmsDsl
    fun codeExample(id: String, builder: TextScope.() -> Unit)

    @LearnCmsDsl
    fun question(id: String, builder: QuestionScope.() -> Unit)
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

fun codeBuilder(builder: CodeBuilderScope.() -> Unit): String {
    val scope = CodeBuilder().apply(builder)
    return scope.build()
}

interface CodeBuilderScope {
    @CodeBuilderDsl
    fun line(text: String)
}
```

Use questions to teach concepts.

### Example usage of DSL
These examples are NOT prescriptive. Use the DSL flexibly and creatively, choosing the most suitable elements and order to achieve the learning goals.
```kotlin
lessonContent {
  // A creative example deviating from strict order
  text("text") {
    text = "Some text"
    style = TextStyle.Heading // Supported ${TextStyle.entries.joinToString(separator = ", ")}
  }

  codeExample("code_snippet") {
    text = codeBuilder {
      // A short Python code snippet
      line("# Let's analyze the time complexity of this function")
      line("def search(array, target):")
      line("  for i in range(len(array)):")
      line("    if array[i] == target:")
      line("      return i")
      line("  return -1")
    }
  }
  
  question("question") {
    question = "Short and well-crafted question" // make sure that it's very concise and straight to the point
    clarification = "A short and simple, thought-provoking sentence guiding you to the right answer"
    answer(text = "Answer 1", explanation = "Explain why it's incorrect")
    answer(text = "Answer 2", correct = true, explanation = "Correct! Explain why it's correct")
    answer(text = "Answer 3", explanation = "Not quite; Explain why not")
    answer(text = "Answer 4", explanation = "Nah; Explain why not")
  }
}
```

The generated lesson should make students say this is the BEST practical explanation of "$topic". 
Ensure technical correctness and readability. Focus on short questions and sentences. 
Follow the best ways the explain the concept from the entire internet to concisely explain "$learningGoal".
Think step by step and ensure clarity and cohesiveness. Ensure the correct every-day, physical examples are picked.
Ensure that the examples are correct and straight-forward to understand.
""".trimIndent()
}
