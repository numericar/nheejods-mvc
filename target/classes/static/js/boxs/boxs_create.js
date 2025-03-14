document.getElementById("incomeSummary").innerHTML = incomeSummary;
document.getElementById("expenseSummary").innerText = expenseSummary;
document.getElementById("remainingSummary").innerText = incomeSummary - expenseSummary;



function addIncomeItem() {
	const titleElement = document.getElementById("incomeTitle");
	const amountElement = document.getElementById("incomeAmount");
		
	const incomeItemsElement = document.getElementById("incomeItems");
	const trElement = document.createElement("tr");
		
	const title = titleElement.value;
	const amount = amountElement.value;
		
	trElement.innerHTML = `
		<td class="border border-2 border-green-700">
			<input type="hidden" name="incomes[${incomeCount}].title" value="${title}">
			<p class="px-5 py-1 w-full">${title}</p>
		</td>
		<td class="border border-2 border-green-700">
			<input type="hidden" name="incomes[${incomeCount}].amount" value="${amount}">
			<p class="px-5 py-1 w-full">${amount}</p>
		</td>
		<td class="border border-2 border-green-700">
												
		</td>
	`;
		
	incomeItemsElement.appendChild(trElement);
		
	titleElement.value = "";
	amountElement.value = "";
		
	incomeCount++;
	
	updateIncome(Number(amount));
	updateRemaining();
}  

function addExpenseItem() {
	const titleElement = document.getElementById("expenseTitle");
	const amountElement = document.getElementById("expenseAmount");
	
	const expenseItemsElement = document.getElementById("expenseItems");
	const trElement = document.createElement("tr");
	
	const title = titleElement.value;
	const amount = amountElement.value;
	
	trElement.innerHTML = `
		<td class="border border-2 border-red-700">
			<input type="hidden" name="expenses[${expenseCount}].title" value="${title}">
			<p class="px-5 py-1 w-full">${title}</p>
		</td>
		<td class="border border-2 border-red-700">
			<input type="hidden" name="expenses[${expenseCount}].amount" value="${amount}">
			<p class="px-5 py-1 w-full">${amount}</p>
		</td>
		<td class="border border-2 border-red-700">
											
		</td>
	`;
	
	expenseItemsElement.appendChild(trElement);
	
	titleElement.value = "";
	amountElement.value = "";
	
	expenseCount++;
	
	updateExpense(Number(amount));
	updateRemaining();
}

function updateIncome(amount) {
	incomeSummary = parseFloat(amount.toFixed(2));

	const incomeSummaryElement = document.getElementById("incomeSummary");
	incomeSummaryElement.innerText = parseFloat(incomeSummary.toFixed(2));
}

function updateExpense(amount) {
	expenseSummary +=  parseFloat(amount.toFixed(2));
	console.log(expenseSummary);
	
	const expenseSummaryElement = document.getElementById("expenseSummary");
	expenseSummaryElement.innerText = parseFloat(expenseSummary.toFixed(2));
}

function updateRemaining() {
	remainingSummary = parseFloat((incomeSummary - expenseSummary).toFixed(2));
	
	document.getElementById("remainingSummary").innerText = remainingSummary;
}

function toggleDropdownMonth() {
    const itemsElement = document.getElementById("monthItems");
    itemsElement.classList.toggle("hidden");
}

function onSelectedMonth(val, id) {
    const monthNameSelectedElement = document.getElementById("monthNameSelected");
    const itemsElement = document.getElementById("monthItems");
    const radioElement = document.getElementById(id);

    monthNameSelectedElement.innerText = val;
    radioElement.checked = true;
    itemsElement.classList.toggle("hidden");
}

function toggleDropdownYear() {
    const itemsElement = document.getElementById("yearItems");
    itemsElement.classList.toggle("hidden");
}

function onSelectedYear(val, id) {
    const yearNameSelectedElement = document.getElementById("yearNameSelected");
    const itemsElement = document.getElementById("yearItems");
    const radioElement = document.getElementById(id);

    yearNameSelectedElement.innerText = val;
    radioElement.checked = true;
    itemsElement.classList.toggle("hidden");
}


