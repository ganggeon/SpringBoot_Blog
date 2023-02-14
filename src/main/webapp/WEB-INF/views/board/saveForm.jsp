<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>
		<div class="form-group">
			<input type="text" class="form-control" placeholder="제목 입력" id="title">
		</div>

		<div class="form-group">
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>


	</form>
		<button id="btn-save" class="btn btn-primary">저장하기</button>
</div>

<script>
	$('.summernote').summernote({
		placeholder : '내용 입력',
		tabsize : 2,
		height : 300
	});
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>