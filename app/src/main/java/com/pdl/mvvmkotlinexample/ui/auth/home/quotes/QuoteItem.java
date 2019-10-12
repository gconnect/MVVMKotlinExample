package com.pdl.mvvmkotlinexample.ui.auth.home.quotes;

 class QuoteItem(private val quote : Quotes) : BindableItem<ItemQuoteBinding>(){

     override fun getLayout()  = R.layout.item_quote

        override fun bind(viewBinding : ItemQuoteBinding, position: Int){
            viewBinding.setQuote(quote)
        }
}
