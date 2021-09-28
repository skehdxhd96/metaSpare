ClassicEditor
	.create( document.querySelector( '#ckeditor' ), {
		
	toolbar: {
		items: [
			'heading',
			'|',
			'fontSize',
			'fontColor',
			'fontBackgroundColor',
			'bold',
			'underline',
			'italic',
			'|',
			'undo',
			'redo',
			'-',
			'bulletedList',
			'numberedList',
			'|',
			'outdent',
			'indent',
			'|',
			'imageUpload',
			'blockQuote',
			'insertTable',
			'mediaEmbed',
			'link'
		],
		shouldNotGroupWhenFull: true
	},
	language: 'ko',
	image: {
		toolbar: [
			'imageTextAlternative',
			'imageStyle:inline',
			'imageStyle:block',
			'imageStyle:side'
		]
	},
	simpleUpload: {
		uploadUrl: '/board/imageSave',
    },
	table: {
		contentToolbar: [
			'tableColumn',
			'tableRow',
			'mergeTableCells'
		]
	},
		licenseKey: '',
		
		
		
	} )
	.then( editor => {
		window.editor = editor;
	} )
	.catch( error => {
		console.error( 'Oops, something went wrong!' );
		console.error( 'Please, report the following error on https://github.com/ckeditor/ckeditor5/issues with the build id and the error stack trace:' );
		console.warn( 'Build id: mup457zd4zfa-7a4uc4qnvsyw' );
		console.error( error );
	} );