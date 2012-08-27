test( "hello test", function() {
  ok( 1 == "1", "Passed!" );
  ok(2=='2',"Passed");
});
test( "hello test2", function() {
  ok( 1 == "1", "Passed!" );
});

var functionNames = [];

for (var f in window) 
{
	if (window.hasOwnProperty(f) && typeof window[f] === 'function')
	{
		functionNames.push(f);
		
	}
}
alert("functions names: " + functionNames[0]);