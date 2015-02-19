<%@ page import="edu.pm.Project" %>



<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="project.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${projectInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="project.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${projectInstance?.code}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'techLeadName', 'error')} required">
	<label for="techLeadName">
		<g:message code="project.techLeadName.label" default="Tech Lead Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="techLeadName" required="" value="${projectInstance?.techLeadName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'projectManagerName', 'error')} required">
	<label for="projectManagerName">
		<g:message code="project.projectManagerName.label" default="Project Manager Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="projectManagerName" required="" value="${projectInstance?.projectManagerName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'deliveryDate', 'error')} required">
	<label for="deliveryDate">
		<g:message code="project.deliveryDate.label" default="Delivery Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="deliveryDate" precision="day"  value="${projectInstance?.deliveryDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'currentPhase', 'error')} required">
	<label for="currentPhase">
		<g:message code="project.currentPhase.label" default="Current Phase" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="currentPhase" from="${edu.pm.ProjectPhase?.values()}" keys="${edu.pm.ProjectPhase.values()*.name()}" required="" value="${projectInstance?.currentPhase?.name()}" />

</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'priority', 'error')} required">
	<label for="priority">
		<g:message code="project.priority.label" default="Priority" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="priority" type="number" value="${projectInstance.priority}" required=""/>

</div>

