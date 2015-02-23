package edu.pm

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import project.management.grails.ProjectService
import spock.lang.Specification

@TestFor(ProjectController)
@Mock([Project, ProjectService])
class ProjectControllerSpec extends Specification {

    def projectService

    def setup() {
        projectService = Mock(ProjectService)
        controller.setProjectService(projectService)
    }

    def "should not create a new project when it is null"() {
        when:
        controller.save(null)

        then:
        Project.count() == 0
    }

    void "Test the index action returns the correct model"() {
        when: "The index action is executed"
        projectService.listOrderByPriority(params) >> []
        projectService.count() >> 0
        controller.index(0)

        then: "The model is correct"
        !model.projectInstanceList
        model.projectInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.projectInstance != null
    }


    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404

        when:"A domain instance is passed to the show action"
        populateValidParams(params)
        def project = new Project(params)
        controller.show(project)

        then:"A model is populated containing the domain instance"
        model.projectInstance == project
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404

        when:"A domain instance is passed to the edit action"
        populateValidParams(params)
        def project = new Project(params)
        projectService.save(project)
        controller.edit(project)

        then:"A model is populated containing the domain instance"
        model.projectInstance == project
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/project/index'
        flash.message != null


        when:"An invalid domain instance is passed to the update action"
        response.reset()
        def project = new Project()
        project.validate()
        controller.update(project)

        then:"The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.projectInstance == project

        when:"A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        project = new Project(params).save(flush: true)
        projectService.save(project)
        controller.update(project)

        then:"A redirect is issues to the show action"
        response.redirectedUrl == "/project/show/$project.id"
        flash.message != null
    }

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'project name'
        params["code"] = 'code'
        params["techLeadName"] = 'John Smith'
        params["projectManagerName"] = 'Adam Jones'
        params["currentPhase"] = 'BRIEFING'
        params["priority"] = '1'

        params["deliveryDate"] = new Date()
    }
}
