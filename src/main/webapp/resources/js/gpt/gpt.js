const urlStrPicked = window.location.href;
const url = new URL(urlStrPicked);


document.addEventListener('DOMContentLoaded', () => {
    const urlParams =url.searchParams
    const urlPrompt= urlParams.get("prompt")

    const searchBar = document.getElementById('search-input');
    const searchButton = document.getElementById('upload-icon-box');
    const chatDisplay = document.getElementById('chat-display');

    let chatDataList = localStorage.getItem('chatList'+urlPrompt) 
        ? JSON.parse(localStorage.getItem('chatList'+urlPrompt)) 
        : [];

    let isLoading = false;

    const renderChats = () => {
        chatDisplay.innerHTML = '';

        //로컬스토리지에서 대화 목록 가져오기
        chatDataList.forEach((chat, index) => {
            const userChatElement = document.createElement('div');
            userChatElement.classList.add('chat-card');
            userChatElement.innerHTML = `
                <div class="chat-avatar">유저</div>
                <div class="chat-card-body">${chat.question}</div>
            `;

            chatDisplay.appendChild(userChatElement);
            chatDisplay.lastElementChild.scrollIntoView({behavior: 'auto'})

            if (chat.message) {
                const botChatElement = document.createElement('div');
                botChatElement.classList.add('chat-card', 'bot-message');
                botChatElement.innerHTML = `
                    <div class="request-card-body">${chat.message}</div>
                    <div class="chat-avatar bot-avatar">
                        <img src="resources/img/default/dog.png">
                    </div>
                  
                `;
                chatDisplay.appendChild(botChatElement);
                chatDisplay.lastElementChild.scrollIntoView({behavior: 'auto'})
            }
        });

       //로딩중이면..
        if (isLoading) {
            const loadingIndicator = document.createElement('div');
            loadingIndicator.classList.add('loading-indicator');
            loadingIndicator.innerHTML = 'AI 응답 작성중... <span class="loading-icon">⏳</span>';
            chatDisplay.appendChild(loadingIndicator);
            chatDisplay.lastElementChild.scrollIntoView({behavior: 'auto'})
        }

        //chatDisplay.scrollTop = chatDisplay.scrollHeight;

        //chatDisplay.lastElementChild.scrollIntoView({behavior: 'smooth',block:'start'})
    };

    const handleChangeSearchText = (ev) => {
        searchText = ev.target.value;
        if (searchText) {
            searchButton.classList.add('active');
        } else {
            searchButton.classList.remove('active');
        }
    };

    const handleClickSearchIcon = async () => {
        if (!searchBar.value) return;

        const chatData = {
            date: new Date().toLocaleString(),
            question: searchBar.value,
            message: ""
        };

        chatDataList.push(chatData);
        renderChats();
        chatDisplay.scrollTop = chatDisplay.scrollHeight;

        searchBar.value = '';
        searchButton.classList.remove('active');

        try {
            isLoading = true;
            renderChats();

            const message = await callGpt(chatData.question);
            chatDataList[chatDataList.length - 1].message = message;
            localStorage.setItem('chatList'+urlPrompt, JSON.stringify(chatDataList));
            isLoading = false;
            renderChats();
        } catch (error) {
            console.error(error);
            isLoading = false;
        } finally {
            searchButton.disabled = false;
            chatDisplay.scrollTop = chatDisplay.scrollHeight;
        }
    };

    searchBar.addEventListener('input', handleChangeSearchText);
    searchButton.addEventListener('click', handleClickSearchIcon);

    renderChats();


//유저가 채팅방에 접속하면 유저가 선택한 프롬포트를 바로 챗봇한테 전달
    const defaultMessage = async (prompt)=>{
        console.log(prompt)
      if (!prompt||prompt==="none") return;
  
          const chatData = {
              date: new Date().toLocaleString(),
              question: prompt,
              message: ""
          };
  
          chatDataList.push(chatData);
          renderChats();
  
  
          try {
              isLoading = true;
              renderChats();
  
              const message = await callGpt(chatData.question);
              chatDataList[chatDataList.length - 1].message = message;
              localStorage.setItem('chatList'+urlPrompt, JSON.stringify(chatDataList));
              isLoading = false;
              renderChats();
          } catch (error) {
              console.error(error);
              isLoading = false;
          } finally {
              searchButton.disabled = false;
              chatDisplay.scrollTop = chatDisplay.scrollHeight;
          }
  }

  defaultMessage(urlPrompt)
  
});



const callGpt = async (prompt) => {
   
    const response= await fetch("chat",{
        method:"POST",
        headers: {
          "Content-Type" : "application/json",
        },
        body:JSON.stringify({
          prompt: prompt
        })
    })

    const responseData = await response.json();
    const message = responseData.choices[0].message.content;
    return message;
};
