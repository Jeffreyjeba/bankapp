	
       <div class="idSpace" id="emp">
        <form>
            <input type="number" placeholder="Chance Id" required>
            <button type="submit">change</button>
        </form>
       </div>
    <script>
    	<% String authString= (String) request.getAttribute("auth");
    		if(authString==null){%>
    		document.getElemetById("emp").style.display="none";
    		<%}
    		else{%>
    		document.getElemetById("emp").style.display="flex";
    		<%}%>
    </script>