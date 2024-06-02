// 변수
const fixExtensionItemButtons = document.querySelectorAll(".fix-extension-item .button");
const customExtensionInput = document.querySelector(".custom-extension-input-area > input");
const fixExtensionInput = document.querySelector(".fix-extension-input-area > input");
const addButton = document.querySelector(".add-button");
const addFixByButton = document.querySelector(".fix-extension-add-button");
const deleteFixByButton = document.querySelector(".fix-extension-delete-button");
let deleteButtons = document.querySelectorAll(".delete");
const resetButtons = document.querySelectorAll(".reset-button");

// 이벤트
/**
 * 고정 확장자 체크박스 클릭 이벤트
 * */
fixExtensionItemButtons.forEach(checkbox => {
  checkbox.addEventListener("click", event => {
    const target = event.currentTarget;
    target.classList.toggle("active");

    const extensionName = target.name;
    const isChecked = target.classList.contains("active").toString();
    updateFixExtensionChecked(extensionName, isChecked);
  });
});

/**
 * 커스텀 확장자 입력 시 버튼 스타일 활성화
 */
customExtensionInput.addEventListener("input", function () {
  let inputValue = customExtensionInput.value.trim();
  addButton.classList.toggle("active", inputValue !== "");
});

/**
 * 고정 확장자 추가 버튼 클릭 시 추가
 */
addFixByButton.addEventListener("click", function () {
  const newFixName = fixExtensionInput.value.trim();
  const fixCount = parseInt(document.querySelector(".fix-current").textContent, 10);
  const fixExtensionList = Array.from(document.querySelectorAll(".fix-extension-item .fix-label"));

  if (fixCount >= 20) {
    alert("고정 확장자 최대 저장 횟수를 초과하였습니다.\n기존 고정 확장자를 삭제해주세요.");
  } else if (fixExtensionList.some(item => item.textContent === newFixName)) {
    alert("이미 존재하는 확장자입니다.");
  } else if (!extensionValidateCheck(newFixName)) {
    alert("영어 대소문자만 입력해주세요. (영어 대문자, 한글, 공백, 특수문자 입력 불가)");
  } else {
    addFixExtension(newFixName);
  }

  fixExtensionInput.value = "";
});

/**
 * 커스텀 확장자 추가 버튼 클릭 시 추가
 */
addButton.addEventListener("click", function () {
  const newCustomName = customExtensionInput.value.trim();
  const customCount = parseInt(document.querySelector(".current").textContent, 10);
  const customExtensionList = Array.from(document.querySelectorAll(".custom-extension-list .custom-extension-name"));

  if (customCount >= 200) {
    alert("커스텀 확장자는 최대 200개까지 등록 가능합니다.");
  } else if (customExtensionList.some(item => item.textContent === newCustomName)) {
    alert("이미 존재하는 확장자입니다.");
  } else if (!extensionValidateCheck(newCustomName)) {
    alert("영어 대소문자만 입력해주세요. (영어 대문자, 한글, 공백, 특수문자 입력 불가)");
  } else {
    addCustomExtension(newCustomName);
  }

  customExtensionInput.value = "";
});

/**
 * 고정 확장자 Enter 입력 시 추가
 */
fixExtensionInput.addEventListener("keyup", (event) => {
  if (event.key === "Enter") {
    addFixByButton.click();
    return false;
  }
});

/**
 * 커스텀 확장자 Enter 입력 시 추가
 */
customExtensionInput.addEventListener("keyup", (event) => {
  if (event.key === "Enter") {
    addButton.click();
    return false;
  }
});

/**
 * 고정 확장자 삭제 버튼 클릭 시 삭제
 * */
deleteFixByButton.addEventListener("click", function () {
  const fixExtensionName = fixExtensionInput.value.trim();;
  const fixExtensionList = Array.from(document.querySelectorAll(".fix-extension-item .fix-label"));
  const fixExtensionExists = fixExtensionList.some(item => item.textContent === fixExtensionName);
  const checkedItem = fixExtensionList.find(item => item.textContent === fixExtensionName);

  // 입력 타입 체크
  if (fixExtensionName) {
    extensionValidateCheck(fixExtensionName);
  }
  // 없는 확장자를 삭제하려는 경우
  if (!fixExtensionExists) {
    alert("고정 확장자 목록에 없는 확장자입니다.");
    fixExtensionInput.value = "";
    return false;
  }
  const checkbox = checkedItem.previousElementSibling;
  // 확장자 checkbox가 check된 상태인 경우
  if (checkbox.checked) {
    alert("확장자를 선택 해제 해주세요.");
    fixExtensionInput.value = "";
    return false;
  }
  // 확장자 삭제 최종 확인
  if (confirm("입력하신 고정 확장자를 리스트에서 삭제하시겠습니까?")) {
    deleteFixExtension(fixExtensionName);
  } else {
    return false;
  }
});

/**
 * 커스텀 확장자 삭제 버튼 클릭 시 삭제
 * */
deleteButtons.forEach((delButton) => {
  delButton.addEventListener("click", function () {
    const extensionName = this.previousElementSibling.textContent;

    deleteCustomExtension(extensionName);
  });
});

/**
 * 커스터 확장자 초기화 버튼 클릭 시 수행
 */
resetButtons.forEach((button) => {
  button.addEventListener("click", function () {

    if (this.classList.contains("custom")) {
      if (confirm("삭제 시 복구할 수 없습니다.\n커스텀 확장자를 모두 삭제하시겠습니까?")) {
        // DB 초기화
        resetCustomExtension();
      } else {
        return false;
      }
      // 확장자 수 초기화
      const currentCount = document.querySelector(".current");
      const customExtensionList = document.querySelector(
        ".custom-extension-list"
      );
      customExtensionList.innerHTML = "";
      currentCount.textContent = 0;
    }
  });
});

// Functions

/**
 * 고정 확장자 checked 필드 업데이트 요청
 * @param extensionName
 * @param isChecked
 */
function updateFixExtensionChecked(extensionName, isChecked) {
  $.ajax({
    url: "fix/check/",
    type: "PUT",
    data: JSON.stringify({ name: extensionName, isChecked: isChecked }),
    contentType: "application/json",
    success: function (result) {
      if (result === true) {
        console.log("고정 확장자 checked 업데이트 성공");
      } else {
        console.log("고정 확장자 checked 업데이트 실패");
      }
    },
    error: function () {
      console.log("updateFixExtensionChecked 요청 실패");
    },
  });
}

function resetCustomExtension() {
  console.log("resetCustomExtension 호출");

  $.ajax({
    url: "custom/reset/",
    type: "DELETE",
    success: function (result) {
      if (result === true) {
        console.log("커스텀 확장자 초기화 성공");
      } else {
        console.log("커스텀 확장자 초기화 실패");
      }
    },
    error: function () {
      console.log("커스텀 확장자 초기화 요청 실패");
    },
  });
}

/**
 * 커스텀 확장자 추가 요청
 * @param newCustomName
 */
function addCustomExtension(newCustomName) {
  $.ajax({
    url: "custom/",
    data: JSON.stringify({ name: newCustomName }),
    type: "POST",
    contentType: "application/json", // 추가된 부분
    success: function (result) {
      if (result === true) {
        console.log("커스텀 확장자 추가 성공");
        location.reload();
      } else {
        console.log("커스텀 확장자 추가 실패");
        customExtensionInput.value = "";
      }
    },
    error: function () {
      customExtensionInput.value = "";
      alert("이미 추가된 확장자입니다!");
      console.log("커스텀 확장자 추가 요청 실패");
    },
  });
}

/**
 * 고정 확장자 추가 요청
 * @param newFixName
 */
function addFixExtension(newFixName) {
  $.ajax({
    url: "fix/",
    data: JSON.stringify({ name: newFixName }),
    type: "POST",
    contentType: "application/json", // 추가된 부분
    success: function (result) {
      if (result === true) {
        console.log("고정 확장자 추가 성공");
        location.reload();
      } else {
        console.log("고정 확장자 추가 실패");
        customExtensionInput.value = "";
        // alert("고정 확장자 기능을 이용해주세요!");
      }
    },
    error: function () {
      customExtensionInput.value = "";
      alert("이미 추가된 확장자입니다!");
      console.log("커스텀 확장자 추가 요청 실패");
    },
  });
}

/**
 * 확장자명 유효성 검사
 * @param name
 * @returns boolean
 */
function extensionValidateCheck(name) {
  const regex = /^[a-zA-Z]+$/;

  if (regex.test(name)) {
    return true;
  } else {
    return false;
  }
}

/**
 * 고정 확장자 삭제 요청
 * @param extensionName
 */
function deleteFixExtension(extensionName) {
  $.ajax({
    url: "fix/",
    data: JSON.stringify({name: extensionName,}),
    type: "DELETE",
    contentType: "application/json", // 추가된 부분
    success: function () {
      console.log("고정 확장자 삭제 성공");
      location.reload();
    },
    error: function () {
      console.log("고정 확장자 삭제 실패");
    },
  });
}

/**
 * 커스텀 확장자 삭제 요청
 * @param extensionName
 */
function deleteCustomExtension(extensionName) {
  $.ajax({
    url: "custom/",
    data: JSON.stringify({name: extensionName,}),
    type: "DELETE",
    contentType: "application/json", // 추가된 부분
    success: function () {
      console.log("커스텀 확장자 삭제 성공");
      location.reload();
    },
    error: function () {
      console.log("커스텀 확장자 삭제 실패");
    },
  });
}
