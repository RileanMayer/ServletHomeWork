<%@ page import="java.util.Queue" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#getState').click(function (){
                $.ajax({
                    type: "get",
                    url: "/myServlet/cool",
                    success: function(msg){$('#output').text(msg);}
                });
            });
            $('#putState').click(function (){
                $.ajax({
                    type: "put",
                    url: "/myServlet/cool",
                    success: function(msg){$('#output').text(msg);}
                });
            });
            $('#postState').click(function (){
                $.ajax({
                    type: "post",
                    url: "/myServlet/cool",
                    // data: "name=" +$('#ip').val(),
                    success: function(msg){$('#output').text(msg);}
                });
            });
            $('#deleteState').click(function (){
                $.ajax({
                    type: "delete",
                    url: "/myServlet/cool",
                    success: function(msg){
                        $('#output').text(msg);
                    }
                });
            });
        });
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <input type="button" value="Get" id="getState"/>
    <input type="button" value="Put" id="putState"/>
    <input type="button" value="Post" id="postState"/>
    <input type="button" value="Delete" id="deleteState"/>
<div id="output"></div>


<br>

</body>
</html>