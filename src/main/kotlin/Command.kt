import java.nio.file.Path

inline class Command(private val command: String) {
    private val parts: Array<String> get() = command.split("\\s".toRegex()).toTypedArray()

    // Credits to: https://stackoverflow.com/questions/35421699/how-to-invoke-external-command-from-within-kotlin-code
    fun executingIn(workingDir: Path): ProcessBuilder = ProcessBuilder(*parts).directory(workingDir.toFile())
}