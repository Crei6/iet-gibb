Rails.application.routes.draw do
  get '/users/show'
  root 'pages#home'
  get 'helloworld', to: 'helloworld#index'
  get '/pages/texte'
  get '/pages/bilder'
  get '/pages/formulare'

  get '/subjects', to: 'subjects#index', as: 'subjects'
  get '/subjects/show/:id', to: 'subjects#show', as: 'show_subject'
  
  get '/books', to: 'books#index', as: 'books'
  get '/books/show/:id', to: 'books#show', as: 'show_book'
  delete '/books/destroy/:id', to: 'books#destroy', as: 'delete_book'
  get '/books/new', to: 'books#new', as: 'new_book'
  post '/books', to: 'books#create'
  get '/books/edit/:id', to: 'books#edit', as: 'edit_book'
  patch '/books/:id', to: 'books#update'

  resources :albums 
  resources :songs
end
