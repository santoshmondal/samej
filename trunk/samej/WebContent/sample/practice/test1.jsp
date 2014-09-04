<%@ taglib uri="/struts-tags" prefix="s"%>

<s:set name="SESSION_KEY" value="@com.samej.common.Constants@SESSION_KEY"></s:set>
	
<!-- Including Header -->
<jsp:include page='/samej/template/header.jsp'></jsp:include>
<div  id="main-container">
	<div class="main-container-inner">
		<!-- Including Navigation -->
		<jsp:include page="/samej/template/leftnav.jsp"></jsp:include>

		<div class="main-content">
			
			<!-- Dynamic Content Goes here-->
			<div class="page-content">
				
				<div id="id_EmbedPage">
					Hello Home!!
					
					<s:iterator var="userRole" value="#session[#SESSION_KEY].roles" >
						<s:iterator var='userAction' value='#userRole.actions'>
							<s:property value='#userAction.actionName'/>
						</s:iterator>
					</s:iterator>
					
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
