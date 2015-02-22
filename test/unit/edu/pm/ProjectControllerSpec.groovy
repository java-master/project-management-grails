package edu.pm



import grails.test.mixin.*
import spock.lang.*

@TestFor(ProjectController)
@Mock(Project)
class ProjectControllerSpec extends Specification {


	def setup() {
		Project projectA = new Project()
	}

	def "should sort the projects by priority in descendant order"() {
		//		controller.index(0)
	}
}
