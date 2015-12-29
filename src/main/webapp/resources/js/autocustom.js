$(document).ready(function () {
    
	  $('#w-input-search').autocomplete({
		  minLength: 2,
		  source: function (request, response) {
		         $.ajax({
		             url: myContextPath+"/GetProjects",
		             data: { projectName: request.term },
		             dataType: "json",
		             success: function (data) {
		                 //   alert("asd");
		                    response($.map(data, function (item) {
		                        return {
		                            label: item.projectName,
		                            value: item.projectName,
		                            id:item.projectId
		                        }
		                    }));
		                },
		             error: function () {
		            	 alert("ERROR!");
		             }
		         });
		     },
		        select: function (event, ui) {
		            //  alert(ui.item.id);
		            window.location.href = myContextPath+"/ProjectDetails?id=" + ui.item.id;
		        }
		});
});