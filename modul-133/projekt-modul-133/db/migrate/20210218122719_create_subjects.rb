class CreateSubjects < ActiveRecord::Migration[6.0]
  def change
    create_table :subjects do |t|
      t.string :name
      t.timestamps
    end

    Subject.create name: "Rails"
    Subject.create name: "Linux"
    Subject.create name: "SQL"
    Subject.create name: "Python"
    Subject.create name: "Bash"
  end
end
