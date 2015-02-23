package project.management.grails

import edu.pm.Project
import edu.pm.ProjectPhase
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import spock.lang.Specification

@TestFor(ProjectService)
@Mock(Project)
class ProjectServiceSpec extends Specification {

    def "should sort the projects by priority in descendant order"() {
        given:
        def project3 = getProject('project 3', 'a3', 3)
        project3.save(flush: true)

        def project1 = getProject('project 1', 'a1', 1)
        project1.save(flush: true)

        def project2 = getProject('project 2', 'a2', 2)
        project2.save(flush: true)

        GrailsParameterMap params = Mock(GrailsParameterMap)

        when:
        def actualProjects = service.listOrderByPriority(params)

        then:
        def expectedProjects = [project1, project2, project3].sort { it.priority }
        actualProjects == expectedProjects
    }

    def getProject(name, code, priority) {
        new Project(name: name,
                code: code,
                techLeadName: 'John Smith',
                projectManagerName: 'Nick Adams',
                deliveryDate: new Date(),
                currentPhase: ProjectPhase.BRIEFING,
                priority: priority)
    }


    def "should create a new project when it does not exist"() {
        given:
        def project = getProject('project', 'prj', 1)

        when:
        service.save(project)

        then:
        Project.count() == 1
    }

    def "should not create a new project when the priority is less than 1"() {
        given:
        def project = getProject('project', 'prj', 0)

        when:
        service.save(project)

        then:
        Project.count() == 0
    }

    def "should not create a new project when there priority is already used by other existing project"() {
        given:
        def project = getProject('project', 'prj', 1)
        project.save(flush: true)

        when:
        service.save(project)

        then:
        Project.count == 1
    }


    def "should update an existing project"() {
        given:
        def project = getProject('project', 'prj', 1)
        project.save(flush: true)


        def newName = 'another project name'
        project.name = newName

        when:
        def savedInstance = service.save(project)

        then:
        Project.count == 1
        Project.findById(savedInstance.id).name == newName
    }

    def "should delete a project when it exists"() {
        given:
        def project = getProject('project', 'prj', 1)
        project.save(flush: true)

        when:
        service.delete(project)

        then:
        Project.count == 0
    }
}
