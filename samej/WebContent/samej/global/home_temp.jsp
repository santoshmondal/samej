<script>
	var params = [
			'directories=no,location=no,channelmode=no,fullscreen=no,menubar=no,resizable=yes,status=no,toolbar=no,history=no,scrollbars=yes',
			'height=' + screen.height, 'width=' + screen.width ].join(',');

	var popup = window.open('home.action', '_blank', params);
	
	if (!popup) {
		alert('Pop-up blocker is enabled !! Please enable it by clicking \'Allow pop-ups to access SAMEJ.\'');
	} else {
		popup.moveTo(0, 0);
		self.close();
	}
</script>