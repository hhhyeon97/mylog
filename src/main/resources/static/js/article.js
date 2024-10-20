
// 삭제 기능
// 1. id가 delete-btn인 엘리먼트 조회
const deleteButton = document.getElementById('delete-btn');
if(deleteButton){
    // 2. 클릭 이벤트가 감지되면 삭제 API 요청
    deleteButton.addEventListener('click', e => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace('/articles');
            });
    });
}

// 수정 기능
// 1. id가 modify-btn인 엘리먼트 조회
const modifyButton = document.getElementById('modify-btn');
if(modifyButton){
    // 2. 클릭 이벤트가 감지되면 수정 API 요청
    modifyButton.addEventListener('click', e=>{
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })

            .then(()=>{
                alert('수정이 완료되었습니다.');
                location.replace(`/articles/${id}`);
            })
    })
}

// 생성 기능
// 1. id가 create-btn인 엘리먼트 조회
const createButton = document.getElementById('create-btn');
if(createButton){
    // 2. 클릭 이벤트가 감지되면 생성 API 요청
    createButton.addEventListener('click', e=>{
        fetch("/api/articles", {
            method: "POST",
            headers: {
                "Content-Type" : "application/json"
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value
            }),
        })
            .then(()=>{
                alert("등록이 완료되었습니다.");
                location.replace("/articles");
            })
    })
}