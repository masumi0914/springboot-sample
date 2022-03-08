$(function() {
  $('.registerButton').on('click', function() {
    submitForm(this);
  });

  $('.downloadButton').on('click', function() {
    // submitForm(this);
  });

  $('.deleteButton').on('click', function() {
    if(! window.confirm('削除します。よろしいですか？')) {
      return;
    }
    submitForm(this);
  });

  function submitForm(_this) {
    var form = $(_this).parents('form');
    form.attr('action', $(_this).data('action'));
    form.submit();
  }
});