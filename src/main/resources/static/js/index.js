// Variables

const fixExtensionItemButtons = $(".fix-extension-item .button");

const customExtensionInput = $(".custom-extension-input-area > input");
const addButton = $(".add-button");
let deleteButtons = $(".delete");

const fileInput = document.getElementById("file");
const fileText = $(".file > span");
const submitButton = $(".submit");

const resetButtons = $(".reset-button");

const Type = {
  Fix: "F",
  Custom: "C",
};

// Events

/**
 * 고정 확장자 버튼 클릭 이벤트
 * */
fixExtensionItemButtons.forEach((button) => {
  button.addEventListener("click", function () {
    // 스타일 적용
    this.classList.toggle("active");

    // Ajax 요청
    const extensionName = this.textContent;
    let isChecked = this.classList.contains("active") ? "N" : "Y";

    updateExtensionChecked(extensionName, isChecked);
  });
});

/**
 * 커스텀 확장자 입력 시 버튼 스타일 활성화
 */
customExtensionInput.addEventListener("input", function (event) {
  const inputValue = customExtensionInput.value;

  if (inputValue === "") {
    addButton.classList.remove("active");
  } else {
    addButton.classList.add("active");
  }
});

/**
 * 커스텀 확장자 추가 버튼 클릭 시 추가
 */
addButton.addEventListener("click", function () {
  const newCustomName = customExtensionInput.value;
  const customCount = document.querySelector(".current").textContent;

  // 커스텀 수 200개 이상 등록 방지
  if (customCount == 200) {
    customExtensionInput.value = "";
    alert("커스텀 확장자 최대 저장 횟수를 초과하였습니다.");
  } else {
    if (extensionValidateCheck(newCustomName)) {
      addCustomExtension(newCustomName);
    } else {
      alert("영문 대소문자를 입력해주세요! (한글, 공백, 특수문자 입력 불가)");
      customExtensionInput.value = "";
    }
  }
});

/**
 * 커스텀 확장자 Enter 입력 시 추가
 */
customExtensionInput.addEventListener("keyup", function (event) {
  if (event.keyCode === 13) {
    event.preventDefault();
    addButton.click();
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
 * [고정 / 커스텀] 초기화 버튼 클릭 시 수행
 */
resetButtons.forEach((button) => {
  button.addEventListener("click", function () {
    if (this.classList.contains("fix")) {
      // DB 초기화
      resetFixedExtension();

      // 스타일 수정
      fixExtensionItemButtons.forEach((item) => {
        item.classList.remove("active");
      });
    } else if (this.classList.contains("custom")) {
      // DB 초기화
      resetCustomExtension();

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
function updateExtensionChecked(extensionName, isChecked) {
  $.ajax({
    url: "fix/update",
    type: "POST",
    data: { name: extensionName, checked: isChecked },
    success: function (result) {
      if (result === true) {
        console.log("고정 확장자 checked 업데이트 성공");
      } else {
        console.log("고정 확장자 checked 업데이트 실패");
      }
    },
    error: function () {
      console.log("updateExtensionChecked 요청 실패");
    },
  });
}

/**
 * 고정 확장자 초기화 요청
 */
function resetFixedExtension() {
  $.ajax({
    url: "fix/reset",
    type: "POST",
    success: function (result) {
      if (result === true) {
        console.log("고정 확장자 초기화 성공");
      } else {
        console.log("고정 확장자 초기화 실패");
      }
    },
    error: function () {
      console.log("고정 확장자 초기화 요청 실패");
    },
  });
}

function resetCustomExtension() {
  console.log("resetCustomExtension 호출");

  $.ajax({
    url: "custom/reset",
    type: "POST",
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
    url: "custom/add",
    data: { name: newCustomName },
    type: "POST",
    success: function (result) {
      if (result === true) {
        console.log("커스텀 확장자 추가 성공");
        location.reload();
      } else {
        console.log("커스텀 확장자 추가 실패");
        customExtensionInput.value = "";
        alert("고정 확장자 기능을 이용해주세요!");
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
 * 커스텀 확장자 삭제 요청
 * @param extensionName
 */
function deleteCustomExtension(extensionName) {
  $.ajax({
    url: "custom/delete",
    data: {
      name: extensionName,
    },
    type: "POST",
    success: function () {
      console.log("custom 삭제 성공");
      location.reload();
    },
    error: function () {
      console.log("custom 삭제 실패");
    },
  });
}

/**
 * 파일 제출 시 확장자명 유효성 검사
 * @param fileExtensionName
 */
function checkValidExtension(fileExtensionName) {
  $.ajax({
    url: "checkValidExtension",
    data: {
      name: fileExtensionName,
    },
    type: "POST",
    success: function (result) {
      if (result === true) {
        alert("제출 성공");
      } else {
        alert("제한된 확장자 입니다.");
      }
    },
    error: function () {
      console.log("checkValidExtension 실패");
    },
  });
}
