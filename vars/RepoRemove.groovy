#!/usr/bin/env groovy

def call(def pkg) {
    pkg.artixConfig.tools.repoRemoveCmd += " -d ${pkg.artixConfig.tools.repoRemoveName}"
    catchError(message: "Errors occurred.", buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {
        if ( params.isDryRun ) {
            echo "${pkg.artixConfig.tools.repoRemoveCmd} ${pkg.pkgInfo.pkgfile.join(' ')}"
        } else {
            sh "${pkg.artixConfig.tools.repoRemoveCmd} ${pkg.pkgInfo.pkgfile.join(' ')}"
        }
    }
}
