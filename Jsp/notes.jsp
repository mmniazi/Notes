<%-- Put any code here --%>
<% code fragment %>

<%-- Declare variables here --%>
<%! declaration; [ declaration; ]+ ... %>

<%-- Expressions --%>
<%= expression %>
<%= (new java.util.Date()).toLocaleString()%>

<%-- A JSP directive affects the overall structure of the servlet class. It usually has the following form: --%>
<%@ directive attribute="value" %>

<%-- Include a jsp --%>
<%@ include file="relative url" >

<%-- unlike include this inserts the file when page is requested instead when the servlet is generated --%>
<jsp:useBean id="name" class="package.class" />

<%-- The useBean action is quite versatile. It first searches for an existing object utilizing the id and scope variables. If an object is not found, it then tries to create the specified object. --%>
<jsp:useBean id="name" class="package.class" />

<%-- The setProperty action sets the properties of a Bean. The Bean must have been previously defined before this action. There are two basic ways to use the setProperty action: --%>

<%-- Outside the bean --%>
<jsp:useBean id="myName" ... />
<jsp:setProperty name="myName" property="someProperty" .../>
