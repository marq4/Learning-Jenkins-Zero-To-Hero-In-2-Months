//def trivy = "trivy-bin/trivy" // Does not work.

def scan(String imageName) {
    def trivy = "trivy-bin/trivy"
    println "Inside shared library vars/trivyScan Groovy vars. Image name: $imageName."
    sh """
        ${trivy} image $imageName --severity CRITICAL --exit-code 1 --format json \
            --output trivy-image-report.json
    """
}

def convertReports() {
    def trivy = "trivy-bin/trivy"
    sh """
        ${trivy} convert \
            --format template \
            --template "@html.tpl" \
            --output trivy-image-report.html trivy-image-report.json
        ${trivy} convert \
            --format template \
            --template "@junit.tpl" \
            --output trivy-image-report.xml trivy-image-report.json
    """
}
