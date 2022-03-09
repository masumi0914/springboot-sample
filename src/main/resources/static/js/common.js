$(function() {
  $('.registerButton').on('click', function() {
    var form = $(this).parents('form');
    form.attr('action', $(this).data('action'));
    form.submit();
  });

  $('.downloadButton').on('click', function() {
    // submitForm(this);
  });

  $('.deleteButton').on('click', function() {
    if(! window.confirm('削除します。よろしいですか？')) {
      return;
    }
    var form = $(this).parents('form');
    form.submit();
  });
});