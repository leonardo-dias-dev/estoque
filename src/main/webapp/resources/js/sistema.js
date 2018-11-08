function configurarTelefone() {
	$('.telefone').mask("?(99) 9999-99999", {reverse: true}).focusout(function (event) {
	      var target, phone, element;

	      target = (event.currentTarget) ? event.currentTarget : event.srcElement;
	      phone = target.value.replace(/\D/g, '');
	      element = $(target);
	      element.unmask();
	      
	      if (phone.length > 10) {
	          element.mask("?(99) 99999-9999", {reverse: true});
	      } else {
	          element.mask("?(99) 9999-99999", {reverse: true});
	      }
	 });
}