package ivy.learn.llm

import ivy.learn.TextStyle

fun main() {
  val prompt = lessonPrompt(
    topic = "Time complexity: What and why?",
    learningGoal = """
Wtf is Big O and why should I care? I need a high-level overview.
   """.trimIndent(),
    analogies = listOf(
      "searching friend's phone in a phonebook",
      "word in a dictionary",
      "brute-forcing 4 digit PIN"
    )
  )
  println("===== PROMPT ====")
  println(prompt)
  println("=====")
}

fun lessonPrompt(
  topic: String,
  learningGoal: String,
  analogies: List<String>? = null,
): String {
  return """
You are an expert computer scientist tasked with creating the best beginner-friendly lesson on **"$topic"** using the provided Kotlin DSL. Your goal is to teach CS students with no prior knowledge the basics of the topic in a way that is clear, practical, and engaging.

### Requirements:
1. **Keep It Simple**: Use short, clear sentences with no complex words. Aim for non-native English speakers.
2. **Focus on Basics**: Teach the foundational concepts needed to understand **"$topic"** and meet the **"$learningGoal"**.
3. **Example-Driven**: Use relatable real-world analogies and simple Python code snippets to explain key concepts.
4. **Interactive Learning**: Make students discover concepts by answering short, well-crafted questions. Questions should feel logical and guide the learner step-by-step.
5. **Question Design**:
   - Each question must have 4 answer choices, with at least one correct answer.
   - Questions should be short and easy to understand.
   - Include clear explanations for each answer, showing why it’s correct or incorrect.
6. **Practical and Logical**: Build a cohesive story using relatable everyday-life analogies, simple examples, and a progression of concepts.
7. **Tone**: Friendly, encouraging, and accessible. Avoid technical jargon or overly academic language.
8. **Technical Accuracy**: Ensure all explanations, analogies, and code examples are correct and easy to follow.
9. **DSL Use**:
   - Follow the provided DSL structure strictly.
   - Ensure all IDs are unique and that the code is clean and error-free.
   
${
    if (analogies != null) {
      "### Preferred analogies:\n${analogies.joinToString(separator = ", ") { "\"$it\"" }}"
    } else {
      ""
    }
  }

### Learning Goal:
Help students confidently achieve **"$learningGoal"**.

### Lesson Design Checklist:
- Use the DSL to add **text**, **code snippets**, and **questions** that work together to form a cohesive learning experience.
- Start with the **importance of $topic**, followed by a gradual introduction using **relatable analogies** and **simple Python examples**.
- Make students think and learn by answering questions that uncover key ideas.
- Prioritize clarity, progression, and engagement.
- Questions, questions, questions. Start with questions and teach by asking questions.

**Outcome**: Create the best online explanation of **"$topic"** that is easy, engaging, and memorable for students.
Your objective is to achieve the learning goal: $learningGoal.

## DSL Reference
```kotlin
interface LessonContentScope {
    @LearnCmsDsl
    fun text(id: String, builder: TextScope.() -> Unit)

    @LearnCmsDsl
    fun code(id: String, builder: CodeScope.() -> Unit)

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

interface CodeScope {
  @LearnCmsDsl
  fun line(codeLine: String)
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

  code("code_example") {
     // A short Python code snippet
     line("# Let's analyze the time complexity of this function")
     line("def search(array, target):")
     line("  for i in range(len(array)):")
     line("    if array[i] == target:")
     line("      return i")
     line("  return -1")
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
""".trimIndent()
}
