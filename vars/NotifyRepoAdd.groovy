#!/usr/bin/env groovy

def call(def pkg) {
    String msg = 'repo-add'

    String msgSubject = "${msg}: ${pkg.pkgInfo.pkgbase.pkgname}"
    String subject = "[${pkg.artixConfig.tools.repoAddName}] ${msgSubject}"
    String bodyAction = "<p>Repo: ${pkg.artixConfig.tools.repoAddName}</p>"
    String bodyInfo = "<p>Packages: </p><p>${pkg.pkgInfo.pkgfile.join('\n')}</p>"
    String bodyRepo = "<p><strong>${msg}</strong></p>"
    String bodyAuthor = "<p>authorName: ${pkg.authorInfo.name}</p><p>authorEmail: ${pkg.authorInfo.email}</p>"
    String bodyUrl = "<p><a href=${BUILD_URL}>${BUILD_URL}</a></p>"
    String sendTo = 'artix-builds@artixlinux.org'

    String body = "${bodyRepo}${bodyAction}${bodyInfo}${bodyAuthor}${bodyUrl}"

//     emailext (
//         body: body,
//         subject: subject,
//         to: sendTo,
//         attachLog: false
//     )
}
