<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
    
<%-- 
	<s:set name="short_key" value="@com.samej.common.Constants@SESSION_KEY"></s:set>
	<s:property value="#short_key"/> <br />
	<s:property value="@com.samej.common.Constants@SESSION_KEY" />
--%>

<!-- Including Header -->
<jsp:include page='/samej/template/header.jsp'></jsp:include>

<div  id="main-container">
	<div class="main-container-inner">
		<!-- Including Navigation -->
		<jsp:include page="/samej/template/leftnav.jsp"></jsp:include>

		<div class="main-content">
			
			<!-- Dynamic Content Goes here-->
			<div class="page-content">
				Hello Sample MultiSelect
				<div id="id_EmbedPage">
					<select id='pre-selected-options' multiple='multiple'>
					  <option value='elem_1' >elem 1</option>
					  <option value='elem_2'>elem 2</option>
					  <option value='elem_3'>elem 3</option>
					  <option value='elem_4' selected>elem 4</option>
					  <option value='elem_100'>elem 100</option>
					</select>
				</div>
			</div>
			<!-- page-content -->

		</div>
		<!-- .main-content -->
	</div>
	<!-- main-container-inner -->
	
</div><!-- main-container -->
	
	
<!-- Including Footer -->
<jsp:include page="/samej/template/footer.jsp"></jsp:include>    



<script type='text/javascript'>
	$('#pre-selected-options').multiSelect();
</script>


