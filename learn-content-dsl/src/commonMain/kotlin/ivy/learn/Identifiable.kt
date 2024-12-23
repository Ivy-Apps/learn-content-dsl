package ivy.learn

interface Identifiable<AId : Id> {
    val id: AId
}

interface Id {
    val value: String
}