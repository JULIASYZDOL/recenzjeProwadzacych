'use client'

import { useRouter} from 'next/navigation';
import Link from 'next/link';

export default function Page2() {
  const router = useRouter();
  const names = ['dr inż. Jan Kowalski', 'prof dr inż. Anna Pawlak', 'mgr inż. Andrzej Nowak'];

  return (
    <div>
      <button type="button" onClick={() => router.back()}>Wróć do wyszukiwarki uczelni</button>
      <div className="container">
          <h1>Lista prowadzących na uczelni: </h1>
          <ul>
            {names.map((name, index) => (
              <li key={index}>
                <Link href={`dashboard/details/${index}`}>
                  {name}
                </Link>
              </li>
            ))}
        </ul>
      </div>
    </div>
  )
}