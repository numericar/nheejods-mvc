let incomeCount = 0;
let expenseCount = 0;


function addIncomeItem() {
	const titleElement = document.getElementById("incomeTitle");
	const amountElement = document.getElementById("incomeAmount");
		
	const incomeItemsElement = document.getElementById("incomeItems");
	const trElement = document.createElement("tr");
		
	const title = titleElement.value;
	const amount = amountElement.value;
		
	trElement.innerHTML = `
		<td class="border border-2 border-green-700">
			<input type="hidden" name="incomes[${expenseCount}].title" value="${title}">
			<p class="px-5 py-1 w-full o">${title}</p>
		</td>
		<td class="border border-2 border-green-700">
			<input type="hidden" name="incomes[${expenseCount}].amount" value="${amount}">
			<p class="px-5 py-1 w-full o">${amount}</p>
		</td>
		<td class="border border-2 border-green-700">
												
		</td>
	`;
		
	incomeItemsElement.appendChild(trElement);
		
	titleElement.value = "";
	amountElement.value = "";
		
	expenseCount++;
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
			<input type="hidden" name="expense[${expenseCount}].title" value="${title}">
			<p class="px-5 py-1 w-full o">${title}</p>
		</td>
		<td class="border border-2 border-red-700">
			<input type="hidden" name="expense[${expenseCount}].amount" value="${amount}">
			<p class="px-5 py-1 w-full o">${amount}</p>
		</td>
		<td class="border border-2 border-red-700">
											
		</td>
	`;
	
	expenseItemsElement.appendChild(trElement);
	
	titleElement.value = "";
	amountElement.value = "";
	
	expenseCount++;
	
}