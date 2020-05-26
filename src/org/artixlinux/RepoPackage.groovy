#!/usr/bin/env groovy

package org.artixlinux
class RepoPackage implements Serializable {

    def steps

    RepoPackage(steps) {
        this.steps = steps
    }

    private Map artixConfig = [:]

    private Map pkgInfo = [:]

    private Map authorInfo = [:]

    private Map jobInfo = [:]

    private List<String> repoListGit = []

    def getArtixConfig() {
        artixConfig
    }

    def getPkgInfo() {
        pkgInfo
    }

    def getAuthorInfo() {
        authorInfo
    }

    def getJobInfo() {
        jobInfo
    }

    private Map mapRepo(String src) {
        Map repoMap = [:]
        if ( src == artixConfig.repos.goblins.arch[0] || src == artixConfig.repos.goblins.arch[1] ) {
            repoMap << [src: artixConfig.repos.goblins.name]
        } else if ( src == artixConfig.repos.gremlins.arch[0] || src == artixConfig.repos.gremlins.arch[1] ) {
            repoMap << [src: artixConfig.repos.gremlins.name]
        } else if ( src == artixConfig.repos.system.arch[0] || src == artixConfig.repos.system.arch[1] ) {
            repoMap << [src: artixConfig.repos.system.name]
        } else if ( src == artixConfig.repos.world.arch[0] || src == artixConfig.repos.world.arch[1] ) {
            repoMap << [src: artixConfig.repos.world.name]
        } else if ( src == artixConfig.repos.galaxyGoblins.arch[0] || src == artixConfig.repos.galaxyGoblins.arch[1] ) {
            repoMap << [src: artixConfig.repos.galaxyGoblins.name]
        } else if ( src == artixConfig.repos.galaxyGremlins.arch[0] || src == artixConfig.repos.galaxyGremlins.arch[1] ) {
            repoMap << [src: artixConfig.repos.galaxyGremlins.name]
        } else if ( src == artixConfig.repos.galaxy.arch[0] || src == artixConfig.repos.galaxy.arch[1] ) {
            repoMap << [src: artixConfig.repos.galaxy.name]
        } else if ( src == artixConfig.repos.lib32Goblins.arch[0] ) {
            repoMap << [src: artixConfig.repos.lib32Goblins.name]
        } else if ( src == artixConfig.repos.lib32Gremlins.arch[0] ) {
            repoMap << [src: artixConfig.repos.lib32Gremlins.name]
        } else if ( src == artixConfig.repos.lib32.arch[0] ) {
            repoMap << [src: artixConfig.repos.lib32.name]
        } else if ( src == artixConfig.repos.kdeWobble.arch[0] || src == artixConfig.repos.kdeWobble.arch[1] ) {
            repoMap << [src: artixConfig.repos.kdeWobble.name]
        } else if ( src == artixConfig.repos.gnomeWobble.arch[0] || src == artixConfig.repos.gnomeWobble.arch[1] ) {
            repoMap << [src: artixConfig.repos.gnomeWobble.name]
        }
        return repoMap
    }

    private Map mapRepos(String src, String dest) {
        Map  repoMap = [:]
        if ( src == artixConfig.repos.gremlins.arch[0] && dest == artixConfig.repos.goblins.arch[0] ) {
            repoMap << [src: artixConfig.repos.goblins.name, dest: artixConfig.repos.gremlins.name]
        } else if ( src == artixConfig.repos.gremlins.arch[1] && dest == artixConfig.repos.goblins.arch[1] ) {
            repoMap << [src: artixConfig.repos.goblins.name, dest: artixConfig.repos.gremlins.name]
        } else if ( src.contains(artixConfig.repos.galaxyGremlins.git) && dest.contains(artixConfig.repos.galaxyGoblins.git) ) {
            repoMap << [src: artixConfig.repos.galaxyGoblins.name, dest: artixConfig.repos.galaxyGremlins.name]
        } else if ( src.contains(artixConfig.repos.lib32Gremlins.git) && dest.contains(artixConfig.repos.lib32Goblins.git) ) {
            repoMap << [src: artixConfig.repos.lib32Goblins.name, dest: artixConfig.repos.lib32Gremlins.name]
        } else if ( src.contains(artixConfig.repos.system.git) && dest == artixConfig.repos.gremlins.arch[0] ) {
            repoMap << [src: artixConfig.repos.gremlins.name, dest: artixConfig.repos.system.name]
        } else if ( src.contains(artixConfig.repos.system.git) && dest == artixConfig.repos.gremlins.arch[1] ) {
            repoMap << [src: artixConfig.repos.gremlins.name, dest: artixConfig.repos.system.name]
        } else if ( src.contains(artixConfig.repos.world.git) && dest == artixConfig.repos.gremlins.arch[0] ) {
            repoMap << [src: artixConfig.repos.gremlins.name, dest: artixConfig.repos.world.name]
        } else if ( src.contains(artixConfig.repos.world.git) && dest == artixConfig.repos.gremlins.arch[1] ) {
            repoMap << [src: artixConfig.repos.gremlins.name, dest: artixConfig.repos.world.name]
        } else if ( src == artixConfig.repos.galaxy.arch[0] && dest.contains(artixConfig.repos.galaxyGremlins.git) ) {
            repoMap << [src: artixConfig.repos.galaxyGremlins.name, dest: artixConfig.repos.galaxy.name]
        } else if ( src == artixConfig.repos.galaxy.arch[1] && dest.contains(artixConfig.repos.galaxyGremlins.git) ) {
            repoMap << [src: artixConfig.repos.galaxyGremlins.name, dest: artixConfig.repos.galaxy.name]
        } else if ( src.contains(artixConfig.repos.lib32.arch[0]) && dest.contains(artixConfig.repos.lib32Gremlins.git) ) {
            repoMap << [src: artixConfig.repos.lib32Gremlins.name, dest: artixConfig.repos.lib32.name]
        } else if ( src == artixConfig.repos.goblins.arch[0] && dest == artixConfig.repos.gremlins.arch[0] ) {
            repoMap << [src: artixConfig.repos.gremlins.name, dest: artixConfig.repos.goblins.name]
        } else if ( src == artixConfig.repos.goblins.arch[1] && dest == artixConfig.repos.gremlins.arch[1] ) {
            repoMap << [src: artixConfig.repos.gremlins.name, dest: artixConfig.repos.goblins.name]
        } else if ( src.contains(artixConfig.repos.galaxyGoblins.git) && dest.contains(artixConfig.repos.galaxyGremlins.git) ) {
            repoMap << [src: artixConfig.repos.galaxyGremlins.name, dest: artixConfig.repos.galaxyGoblins.name]
        } else if ( src.contains(artixConfig.repos.lib32Goblins.git) && dest.contains(artixConfig.repos.lib32Gremlins.git) ) {
            repoMap << [src: artixConfig.repos.lib32Gremlins.name, dest: artixConfig.repos.lib32Goblins.name]
        } else if ( src == artixConfig.repos.gremlins.arch[0] && dest.contains(artixConfig.repos.system.git) ) {
            repoMap << [src: artixConfig.repos.system.name, dest: artixConfig.repos.gremlins.name]
        } else if ( src == artixConfig.repos.gremlins.arch[1] && dest.contains(artixConfig.repos.system.git) ) {
            repoMap << [src: artixConfig.repos.system.name, dest: artixConfig.repos.gremlins.name]
        } else if ( src == artixConfig.repos.gremlins.arch[0] && dest.contains(artixConfig.repos.world.git) ) {
            repoMap << [src: artixConfig.repos.world.name, dest: artixConfig.repos.gremlins.name]
        } else if ( src == artixConfig.repos.gremlins.arch[1] && dest.contains(artixConfig.repos.world.git) ) {
            repoMap << [src: artixConfig.repos.world.name, dest: artixConfig.repos.gremlins.name]
        } else if ( src.contains(artixConfig.repos.galaxyGremlins.git) && dest == artixConfig.repos.galaxy.arch[0] ) {
            repoMap << [src: artixConfig.repos.galaxy.name, dest: artixConfig.repos.galaxyGremlins.name]
        } else if ( src.contains(artixConfig.repos.galaxyGremlins.git) && dest == artixConfig.repos.galaxy.arch[1] ) {
            repoMap << [src: artixConfig.repos.galaxy.name, dest: artixConfig.repos.galaxyGremlins.name]
        } else if ( src.contains(artixConfig.repos.lib32Gremlins.git) && dest.contains(artixConfig.repos.lib32.arch[0]) ) {
            repoMap << [src: artixConfig.repos.lib32.name, dest: artixConfig.repos.lib32Gremlins.name]
        } else if ( src.contains(artixConfig.repos.world.git) && dest.contains(artixConfig.repos.system.git) ) {
            repoMap << [src: artixConfig.repos.system.name, dest: artixConfig.repos.world.name]
        } else if ( src.contains(artixConfig.repos.system.git) && dest.contains(artixConfig.repos.world.git) ) {
            repoMap << [src: artixConfig.repos.world.name, dest: artixConfig.repos.system.name]
        } else if ( src == artixConfig.repos.galaxy.arch[0] && dest.contains(artixConfig.repos.system.git) ) {
            repoMap << [src: artixConfig.repos.system.name, dest: artixConfig.repos.galaxy.name]
        } else if ( src == artixConfig.repos.galaxy.arch[1] && dest.contains(artixConfig.repos.system.git) ) {
            repoMap << [src: artixConfig.repos.system.name, dest: artixConfig.repos.galaxy.name]
        } else if ( src.contains(artixConfig.repos.system.git) && dest == artixConfig.repos.galaxy.arch[0] ) {
            repoMap << [src: artixConfig.repos.galaxy.name, dest: artixConfig.repos.system.name]
        } else if ( src.contains(artixConfig.repos.system.git) && dest == artixConfig.repos.galaxy.arch[1] ) {
            repoMap << [src: artixConfig.repos.galaxy.name, dest: artixConfig.repos.system.name]
        } else if ( src == artixConfig.repos.galaxy.arch[0] && dest.contains(artixConfig.repos.world.git) ) {
            repoMap << [src: artixConfig.repos.world.name, dest: artixConfig.repos.galaxy.name]
        } else if ( src == artixConfig.repos.galaxy.arch[1] && dest.contains(artixConfig.repos.world.git) ) {
            repoMap << [src: artixConfig.repos.world.name, dest: artixConfig.repos.galaxy.name]
        } else if ( src.contains(artixConfig.repos.world.git) && dest == artixConfig.repos.galaxy.arch[0] ) {
            repoMap << [src: artixConfig.repos.galaxy.name, dest: artixConfig.repos.world.name]
        } else if ( src.contains(artixConfig.repos.world.git) && dest == artixConfig.repos.galaxy.arch[1] ) {
            repoMap << [src: artixConfig.repos.galaxy.name, dest: artixConfig.repos.world.name]
        } else if ( src.contains(artixConfig.repos.galaxyGoblins.git) && dest == artixConfig.repos.goblins.arch[0] ) {
            repoMap << [src: artixConfig.repos.goblins.name, dest: artixConfig.repos.galaxyGoblins.name]
        } else if ( src.contains(artixConfig.repos.galaxyGoblins.git) && dest == artixConfig.repos.goblins.arch[1] ) {
            repoMap << [src: artixConfig.repos.goblins.name, dest: artixConfig.repos.galaxyGoblins.name]
        } else if ( src  == artixConfig.repos.goblins.arch[0] && dest.contains(artixConfig.repos.galaxyGoblins.git) ) {
            repoMap << [src: artixConfig.repos.galaxyGoblins.name, dest: artixConfig.repos.goblins.name]
        } else if ( src  == artixConfig.repos.goblins.arch[1] && dest.contains(artixConfig.repos.galaxyGoblins.git) ) {
            repoMap << [src: artixConfig.repos.galaxyGoblins.name, dest: artixConfig.repos.goblins.name]
        } else if ( src.contains(artixConfig.repos.galaxyGremlins.git) && dest == artixConfig.repos.gremlins.arch[0] ) {
            repoMap << [src: artixConfig.repos.gremlins.name, dest: artixConfig.repos.galaxyGremlins.name]
        } else if ( src.contains(artixConfig.repos.galaxyGremlins.git) && dest == artixConfig.repos.gremlins.arch[1] ) {
            repoMap << [src: artixConfig.repos.gremlins.name, dest: artixConfig.repos.galaxyGremlins.name]
        } else if ( src  == artixConfig.repos.gremlins.arch[0] && dest.contains(artixConfig.repos.galaxyGremlins.git) ) {
            repoMap << [src: artixConfig.repos.galaxyGremlins.name, dest: artixConfig.repos.gremlins.name]
        } else if ( src  == artixConfig.repos.gremlins.arch[1] && dest.contains(artixConfig.repos.galaxyGremlins.git) ) {
            repoMap << [src: artixConfig.repos.galaxyGremlins.name, dest: artixConfig.repos.gremlins.name]
        } else if ( src  == artixConfig.repos.goblins.arch[0] && dest == artixConfig.repos.kdeWobble.arch[0] ) {
            repoMap << [src: artixConfig.repos.kdeWobble.name, dest: artixConfig.repos.goblins.name]
        } else if ( src  == artixConfig.repos.goblins.arch[1] && dest == artixConfig.repos.kdeWobble.arch[1] ) {
            repoMap << [src: artixConfig.repos.kdeWobble.name, dest: artixConfig.repos.goblins.name]
        } else if ( src  == artixConfig.repos.goblins.arch[0] && dest == artixConfig.repos.gnomeWobble.arch[0] ) {
            repoMap << [src: artixConfig.repos.gnomeWobble.name, dest: artixConfig.repos.goblins.name]
        } else if ( src  == artixConfig.repos.goblins.arch[1] && dest == artixConfig.repos.gnomeWobble.arch[1] ) {
            repoMap << [src: artixConfig.repos.gnomeWobble.name, dest: artixConfig.repos.goblins.name]
        } else if ( src  == artixConfig.repos.kdeWobble.arch[0] && dest == artixConfig.repos.goblins.arch[0] ) {
            repoMap << [src: artixConfig.repos.goblins.name, dest: artixConfig.repos.kdeWobble.name]
        } else if ( src  == artixConfig.repos.kdeWobble.arch[1] && dest == artixConfig.repos.goblins.arch[1] ) {
            repoMap << [src: artixConfig.repos.goblins.name, dest: artixConfig.repos.kdeWobble.name]
        } else if ( src  == artixConfig.repos.gnomeWobble.arch[0] && dest == artixConfig.repos.goblins.arch[0] ) {
            repoMap << [src: artixConfig.repos.goblins.name, dest: artixConfig.repos.gnomeWobble.name]
        } else if ( src  == artixConfig.repos.gnomeWobble.arch[1] && dest == artixConfig.repos.goblins.arch[1] ) {
            repoMap << [src: artixConfig.repos.goblins.name, dest: artixConfig.repos.gnomeWobble.name]
        }
        return repoMap
    }

    private void repoPkgOp() {

        String srcRepo = repoListGit[0].path.tokenize('/')[1]

        if ( repoListGit[0].status == 'A' || repoListGit[0].status == 'M' ) {
            artixConfig.actions.isBuild = true
            artixConfig.tools.repoAddName = mapRepo(srcRepo).src
            artixConfig.tools.repoName = artixConfig.tools.repoAddName
        } else if ( repoListGit[0].status == 'D' ) {
            artixConfig.actions.isRemove = true
            artixConfig.tools.repoRemoveName = mapRepo(srcRepo).src
            artixConfig.tools.repoName = artixConfig.tools.repoRemoveName
        }

        if ( steps.fileExists(repoListGit[0].path + '/PKGBUILD') ) {
            artixConfig.tools.repoPathGit = repoListGit[0].path
        }

    }

    private void repoPkgMove(){

        String srcRepo = repoListGit[0].path.tokenize('/')[1]
        String destRepo = repoListGit[1].path.tokenize('/')[1]

        if ( repoListGit[0].status == 'M' ) {
            artixConfig.actions.isAdd = true
            artixConfig.tools.repoAddName = mapRepo(srcRepo).src
            artixConfig.tools.repoPathGit = repoListGit[1].path
        } else if ( repoListGit[1].status == 'M' ) {
            artixConfig.actions.isAdd = true
            artixConfig.tools.repoAddName = mapRepo(destRepo).src
            artixConfig.tools.repoPathGit = repoListGit[0].path
        }

        if ( repoListGit[0].status == 'D' ) {
            artixConfig.actions.isRemove = true
            artixConfig.tools.repoRemoveName = mapRepo(srcRepo).src
            artixConfig.tools.repoPathGit = repoListGit[1].path
        } else if ( repoListGit[1].status == 'D' ) {
            artixConfig.actions.isRemove = true
            artixConfig.tools.repoRemoveName = mapRepo(destRepo).src
            artixConfig.tools.repoPathGit = repoListGit[0].path
        }

        if ( repoListGit[0].status.contains('R') && repoListGit[1].status.contains('R') )  {
            artixConfig.actions.isAdd = true
            artixConfig.actions.isRemove = true
            artixConfig.tools.repoAddName = mapRepos(srcRepo, destRepo).src
            artixConfig.tools.repoRemoveName = mapRepos(srcRepo, destRepo).dest
            artixConfig.tools.repoPathGit = repoListGit[1].path
        }

        artixConfig.tools.repoName = artixConfig.tools.repoAddName
    }

    private void loadResources(){
        def conf = steps.libraryResource('org/artixlinux/artixConfig.yaml')
        artixConfig = steps.readYaml(text: conf)
    }

    private void loadChangeSet() {
        String gitCmd = 'git rev-parse @'
        String commit = steps.sh(returnStdout: true, script: gitCmd).trim()

        gitCmd = "git show -s --format='%an' ${commit}"
        String authorName = steps.sh(returnStdout: true, script: gitCmd)

        String authorGpg = "GPG_" + authorName.toUpperCase()

        gitCmd = "git show -s --format='%ae' ${commit}"
        String authorEmail = steps.sh(returnStdout: true, script: gitCmd)

        authorInfo = [name: authorName, email: authorEmail, gpgkey: authorGpg]

        gitCmd = "git show --pretty=format: --name-status ${commit}"
        List<String> changeSet = steps.sh(returnStdout: true, script: gitCmd).tokenize('\n')

        for ( int i = 0; i < changeSet.size(); i++ ) {
            List<String> entry = changeSet[i].split()
            String fileStatus = entry[0]
            for ( int j = 1; j < entry.size(); j++ ) {
                if ( entry[j].contains('/PKGBUILD') && entry[j].contains('repos/') ){
                    Map dataSet = [status: fileStatus, path: entry[j].minus('/PKGBUILD')]
                    repoListGit.add(dataSet)
                }
            }
        }
    }

    private void loadPkgYaml() {

        String pkgYaml = steps.sh(returnStdout: true, script: "${artixConfig.tools.yamlCmd} ${artixConfig.tools.repoPathGit}")

        pkgInfo = steps.readYaml(text: pkgYaml)

        jobInfo << [name: "${artixConfig.tools.repoName}", desc: "${pkgInfo.pkgbase.pkgname}-${pkgInfo.pkgbase.fullver}"]
    }

    void initialize() {

        loadResources()

        loadChangeSet()

        byte repoCount = repoListGit.size()

        if ( repoCount > 0 ) {

            if ( repoCount == 1 ) {

                repoPkgOp()

            } else if ( repoCount == 2 ) {

                repoPkgMove()
            }
        }
        loadPkgYaml()
    }
}
