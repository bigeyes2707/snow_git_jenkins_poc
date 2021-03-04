// Sample script with simple workflow of publishing and installing an application on NOW platform

pipeline {
    agent any

    parameters {
            snParam(credentialsForPublishedApp: "88dbbe69-0e00-4dd5-838b-2fbd8dfedeb4", instanceForPublishedAppUrl: "https://comcastdemo.service-now.com",
                    credentialsForInstalledApp:"88dbbe69-0e00-4dd5-838b-2fbd8dfedeb4", instanceForInstalledAppUrl:"https://comcastdemo.service-now.com",
                    appScope: "global")
    }

    stages {
        stage('publishing') {
            steps {
                snPublishApp obtainVersionAutomatically: true
            }
        }
        stage('installation') {
            steps {
                snInstallApp()
                snRunTestSuite browserName: 'Firefox', osName: 'Windows', osVersion: '10', testSuiteName: 'My CHG:Change Management', withResults: true
            }
        }
    }
}
