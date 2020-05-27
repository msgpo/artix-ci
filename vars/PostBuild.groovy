#!/usr/bin/env groovy

def call(def pkg) {
    if ( params.isDryRun ) {
        echo "${pkg.artixConfig.tools.signCmd} ${pkg.pkgInfo.pkgfile.join(' ')}"
    } else {
        sh "${pkg.artixConfig.tools.signCmd} ${pkg.pkgInfo.pkgfile.join(' ')}"
    }

    pkg.artixConfig.actions.isAdd = true

    archiveArtifacts(allowEmptyArchive: true, artifacts: 'repos/**/*.log')
}
