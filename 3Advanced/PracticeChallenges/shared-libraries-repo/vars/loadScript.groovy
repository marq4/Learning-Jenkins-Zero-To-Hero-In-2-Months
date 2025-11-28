def call(Map args = [:]) {
    // Static resource content is loaded as String:
    def scriptData = libraryResource("scripts/${args.scriptName}")
    // Save it to workspace:
    writeFile(file: "${args.scriptName}", text: scriptData)
    // Make it executable:
    sh " chmod +x ./${args.scriptName} "
}
