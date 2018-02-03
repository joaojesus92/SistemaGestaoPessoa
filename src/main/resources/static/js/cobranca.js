$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	
	var idPessoa = button.data('id');
	var nomePessoa = button.data('nome');
	
	alert(idPessoa);
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith('/')){
		action += '/';
	}
	form.attr('action', action + idPessoa);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir a pessoa <strong>' + nomePessoa + '</strong>?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();
});