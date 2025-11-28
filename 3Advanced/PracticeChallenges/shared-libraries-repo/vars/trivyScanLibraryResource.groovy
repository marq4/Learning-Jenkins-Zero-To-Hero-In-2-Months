def scan(Map args = [:]) {
    loadScript(scriptName: 'trivy.sh')
    sh " ./trivy.sh ${args.imageName} ${args.severity} ${args.exitCode} "
}
