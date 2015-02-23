package project.management.grails

import edu.pm.Project
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class ProjectService {

    def listOrderByPriority(GrailsParameterMap params) {
        Project.listOrderByPriority(params)
    }

    def count() {
        Project.count()
    }

    def save(Project project) {
        project.save flush:true
    }

    def delete(Project project) {
        project.delete flush:true
    }

}
