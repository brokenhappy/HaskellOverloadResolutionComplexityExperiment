import java.nio.file.Path

interface Environment {
    val baseProjectFolder: Path
    val haskellProjectName: String
    val swiftProjectName: String
}