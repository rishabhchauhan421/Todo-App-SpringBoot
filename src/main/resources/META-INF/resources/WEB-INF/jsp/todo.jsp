<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<h2>Enter Todo Details</h2>
	<form:form method="post" modelAttribute="todo">
		<fieldset class="mb-3">
			<form:label path="description">Description</form:label>
			<form:input type="text" name="description" required="required" path="description"/>
			<form:errors path="description" css-class="text-warning"></form:errors>
		</fieldset>
		<fieldset class="mb-3">
			<form:label path="targetDate">Target Date</form:label>
			<form:input type="text" name="targetDate" required="required" path="targetDate"/>
			<form:errors path="targetDate" css-class="text-warning"></form:errors>
		</fieldset>
		<form:input type="hidden" path="id"/>
		<form:input type="hidden" path="done"/>
		<input type="submit" class="btn btn-success"/>
	
	</form:form>
</div>
<%@ include file="common/footer.jspf" %>
<script type="text/javascript">
	$('#targetDate').datepicker({
	    format: 'yyyy-mm-dd'
	});
</script>