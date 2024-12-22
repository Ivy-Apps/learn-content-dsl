package ivy.learn

fun nameToId(name: String): String = name
  .lowercase()
  .replace(Regex("[^a-z0-9\\s-]"), "") // Remove invalid characters
  .replace("\\s+".toRegex(), "-")      // Replace spaces with dashes
  .replace("-+".toRegex(), "-")        // Replace multiple dashes with a single dash
  .trim('-')                           // Remove leading and trailing dashes
