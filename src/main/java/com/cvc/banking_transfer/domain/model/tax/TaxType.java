package com.cvc.banking_transfer.domain.model.tax;

public enum TaxType {
	
	A(new ATax()),
	B(new BTax()),
	C(new CTax());
	
	private TaxCalculation taxRule;
	
	TaxType(TaxCalculation taxRule){
		this.taxRule = taxRule;
	}

	public TaxCalculation getRule() {
		return taxRule;
	}


}
