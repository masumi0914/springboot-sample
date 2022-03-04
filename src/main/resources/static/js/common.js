$(function() {
  $('.deleteButton').on('click', function() {
    if(! window.confirm('削除します。よろしいですか？')) {
      return;
    }
    var form = $(this).parents('form');
    form.submit();
  });
});