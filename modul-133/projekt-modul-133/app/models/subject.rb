class Subject < ApplicationRecord
    has_many :books, dependent: :restrict_with_error
end
