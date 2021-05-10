import java.io.File

interface Environment {
    val baseProjectFolder: File
    val haskellProjectName: String
}