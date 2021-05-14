package compileExperiments

import Command
import Environment
import java.nio.file.Path

abstract class SwiftCompileExperiment(private val environment: Environment): CompileExperiment {
    override val basePath: Path get() =
        Path.of("${environment.baseProjectFolder}/${environment.swiftProjectName}")
    override val executionCommand: Command
        get() = Command("swiftc ${environment.baseProjectFolder}/${environment.swiftProjectName}/test.swift")
    override val compiledFilePath: Path
        get() = Path.of("test.swift")
}